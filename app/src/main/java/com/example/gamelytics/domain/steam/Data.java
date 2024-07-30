package com.example.gamelytics.domain.steam;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Data implements Serializable{

        @SerializedName("detailed_description")
        private String detailedDescription;

        @SerializedName("website")
        private String website;

        @SerializedName("required_age")
        private int requiredAge;

        @SerializedName("platforms")
        private Platforms platforms;

        @SerializedName("release_date")
        private ReleaseDate releaseDate;

        @SerializedName("screenshots")
        private List<Screenshot> screenshots;

        @SerializedName("pc_requirements")
        private PcRequirements pcRequirements;

        public String getDetailedDescription() {
            return detailedDescription;
        }

        public void setDetailedDescription(String detailedDescription) {
            this.detailedDescription = detailedDescription;
        }

        public String getWebsite() {
        return website;
    }

        public void setWebsite(String website) {
            this.website = website;
        }

        public int getRequiredAge() {
            return requiredAge;
        }

        public void setRequiredAge(int requiredAge) {
            this.requiredAge = requiredAge;
        }

        public Platforms getPlatforms() {
            return platforms;
        }

        public void setPlatforms(Platforms platforms) {
            this.platforms = platforms;
        }

        public ReleaseDate getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(ReleaseDate releaseDate) {
            this.releaseDate = releaseDate;
        }

        public List<Screenshot> getScreenshots() {
            return screenshots;
        }

        public void setScreenshots(List<Screenshot> screenshots) {
            this.screenshots = screenshots;
        }

        public PcRequirements getPcRequirements() {
            return pcRequirements;
        }

        public void setPcRequirements(PcRequirements pcRequirements) {
            this.pcRequirements = pcRequirements;
        }
}
