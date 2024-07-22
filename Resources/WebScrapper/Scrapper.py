from requests_html import AsyncHTMLSession
import argparse as argp

# Error codes
HTTP_ERROR_STATUS = -1
EMPTY_SOUP_OBJECT = -2
EMPTY_CNTRY_CARDS = -3

# Global variables
session = AsyncHTMLSession()
ctry_cards = {}

async def gift_card_request():
    shop_url = "https://gg.deals/prepaids/?kind=4&platform=7&type=11"
    response = await session.get(shop_url)
    if response.status_code == 200:
        table = response.html.find('#games-list', first = True)
        tabs = table.find('.item-prepaid')
        for tab in tabs:
            country = tab.find('.game-info-title', first = True)
            editions = tab.find('.game-details-editions', first = True)
            ctry_text = country.find("span", first = True).text
            edis_text = editions.find("span", first = True).text

            country_name = ctry_text[29:]
            country_coin = ctry_text[23:26]

            ctry_cards[len(ctry_cards) + 1] = {
                                                "country":  country_name,
                                                "values": {
                                                    "coin": country_coin,
                                                    "edis": []
                                                }
                                            }

async def retrieve_country_editions(coin: str, country: str):
    string = coin + " - " + country
    global idx  # Export variable for only showing up country data
    idx = dictionary_search_country(string[6:]) # index to consult dict data
    # Convert string to lowercase and remove extra spaces. Needed spaces must be '-'
    hyphen_idx = string.find('-')
    string = string[:hyphen_idx - 1] + '' + string[hyphen_idx + 1:]
    converted = string.replace(' ', '-')
    converted = converted.lower()

    url = "https://gg.deals/gift-cards-group/steam-wallet-gift-card-" + converted
    response = await session.get(url)
    if response.status_code == 200:
        table = response.html.find('#games-list', first = True)
        rows = table.find('.game-info-related')
        for row in rows:
            title = row.find('.game-info-title', first = True).text
            price_div = row.find('.game-price', first = True)
            price = price_div.find("span", first = True).text
            price = price.replace('~', '')
            title = title.replace("Steam Wallet Gift Card ", "")
            title = title.replace(" - ", " ")

            ctry_cards[idx]['values']['edis']\
                .append({
                    "edition":  title,
                    "price":    price,
                    "data":     []
                    })
    
async def obtain_prices_for_card(amount: int, coin: str, country: str):
    string = str(amount) + " " + coin + " - " + country
    idx = dictionary_search_country(string[10:]) # index to consult dict data
    # Convert string to lowercase and remove extra spaces. Needed spaces must be '-'
    hyphen_idx = string.find('-')
    string = string[:hyphen_idx - 1] + '' + string[hyphen_idx + 1:]
    converted = string.replace(' ', '-')
    converted = converted.lower()

    url = "https://gg.deals/gift-card/steam-wallet-gift-card-" + converted
    response = await session.get(url)
    if response.status_code == 200:
        table = response.html.find('.load-more-content', first = True)
        rows = table.find('.similar-deals-container')
        for row in rows:
            store = row.find('.shop-image-white', first = True).attrs
            store = store['alt']
            link = row.find('.action-desktop-btn', first = True).attrs
            link = "https://gg.deals" + link['href']
            dictionary_write_edition(string, idx, store, link)


def dictionary_search_country(country_name):
    for key, value in ctry_cards.items():
        if value['country'] == country_name:
            return key
        

def dictionary_write_edition(edition_name, country_index, shop, url):
    for elem in ctry_cards[country_index]['values']['edis']:
        if elem['edition'] == edition_name:
            elem['data'].append({
                "shop": shop,
                "URL": url
            })


def main(amount, currency, country):
    session.run(gift_card_request)
    print(f"------------------------Dictionary data initialized------------------------------")
    session.run(*[lambda coin=currency, country=country: retrieve_country_editions(coin, country)])
    print(f"------------------------Retrieved cards for country {country}--------------------")
    session.run(*[lambda amount=amount, coin=currency, country=country: obtain_prices_for_card(amount, coin, country)])
    print(f"------------------------Retrieved stores for card valued {amount} {currency}-----------------")
    print(ctry_cards[idx])


parser = argp.ArgumentParser(description="Search for price, stores and shopping urls for a Steam gift card of a\n"
                                        + "given country and its currency amount")
parser.add_argument('-c', '--country', type=str, help="Name of the country to search gift cards for")
parser.add_argument('-cc', '--coin', type=str, help="3 character code for the country currency")
parser.add_argument('-amt', '--amount', type=int, help="Gift card's currency value whose information is retrieved")
args = parser.parse_args()

main(args.amount, args.coin, args.country)