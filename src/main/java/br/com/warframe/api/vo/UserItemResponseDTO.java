package br.com.warframe.api.vo;

public class UserItemResponseDTO {
    private Long itemId;
    private String itemName;
    private String itemDescricao;
    private String wikiaThumbnail;
    private int quantidade;

    public UserItemResponseDTO(Long itemId, String itemName, String itemDescricao, String wikiaThumbnail,
            int quantidade) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescricao = itemDescricao;
        this.wikiaThumbnail = wikiaThumbnail;
        this.quantidade = quantidade;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescricao() {
        return itemDescricao;
    }

    public void setItemDescricao(String itemDescricao) {
        this.itemDescricao = itemDescricao;
    }

    public String getWikiaThumbnail() {
        return wikiaThumbnail;
    }

    public void setWikiaThumbnail(String wikiaThumbnail) {
        this.wikiaThumbnail = wikiaThumbnail;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}