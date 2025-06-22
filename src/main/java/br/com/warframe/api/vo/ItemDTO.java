package br.com.warframe.api.vo;

public class ItemDTO {

    private String uniqueName;
    private String name;
    private String type;
    private String wikiaThumbnail;
    private boolean tradable;
    private String category;
    private String description;

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWikiaThumbnail() {
        return wikiaThumbnail;
    }

    public void setWikiaThumbnail(String wikiaThumbnail) {
        this.wikiaThumbnail = wikiaThumbnail;
    }

    public boolean isTradable() {
        return tradable;
    }

    public void setTradable(boolean tradable) {
        this.tradable = tradable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}