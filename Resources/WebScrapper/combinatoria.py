import Scrapper
import argparse
import time

def coin_change(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0
    used_coins = [-1] * (amount + 1)
    for coin in coins:
        for x in range(coin, amount + 1):
            if dp[x - coin] + 1 < dp[x]:
                dp[x] = dp[x - coin] + 1
                used_coins[x] = coin
    minimum_coins = dp[amount]
    coins_used = []
    while amount > 0:
        coins_used.append(used_coins[amount])
        amount -= used_coins[amount]
    return minimum_coins, coins_used

def get_all_editions():
    editions = []
    for country_data in Scrapper.ctry_cards.values():
        for edition_data in country_data['values']['edis']:
            editions.append(edition_data['edition'])
    return editions

parser = argparse.ArgumentParser(description="Program checking possible shop options for the given country as parameter")
parser.add_argument('-c', '--country', type=str, help="Name of the country to search gift cards for")
parser.add_argument('-cc', '--coin', type=str, help="3 character code for the country currency")
args = parser.parse_args()

Scrapper.session.run(Scrapper.gift_card_request)
Scrapper.session.run(*[lambda coin=args.coin, country=args.country: Scrapper.retrieve_country_editions(coin, country)])

start = time.time()
all_editions = get_all_editions()
print("Ediciones de tarjetas disponibles:", all_editions)
numbers = []
print("-------------------------------------------------------------------------------")

for card_value in all_editions:
    # Split the string by spaces and get the numerical part
    value = card_value.split()[0]
    # Convert the numerical part to an integer
    number = int(value)
    # Append the number to the numbers list
    numbers.append(number)


print("Lista de precios de tarjetas disponibles:", sorted(numbers))
minval = min(numbers)
numbers.append(1)
numbers.append(2)


# Ejemplo de uso
amount = 1001
minimum_coins, used_coins = coin_change(numbers, amount)
sorted_used_coins = sorted(used_coins)

for i in range(len(sorted_used_coins)):
    if sorted_used_coins[0] < minval:
        sorted_used_coins.remove(sorted_used_coins[0])
sorted_used_coins.append(minval)
print("Cantidad mínima de monedas necesarias:", sorted(sorted_used_coins) , "para el precio de", amount)
print("Monedas utilizadas:", len(sorted_used_coins))
end = time.time()
print("Tiempo de ejecución:", end - start , "segundos")