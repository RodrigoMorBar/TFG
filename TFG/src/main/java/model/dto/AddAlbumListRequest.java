package model.dto;

public class AddAlbumListRequest {
	
	private Integer identificadorLista;
	private String albumSpotifyId;
	
	
	public AddAlbumListRequest() {
    }

    public AddAlbumListRequest(Integer identificadorLista, String albumSpotifyId) {
        this.identificadorLista = identificadorLista;
        this.albumSpotifyId = albumSpotifyId;
    }

    public Integer getIdentificadorLista() {
        return identificadorLista;
    }

    public void setIdentificadorLista(Integer identificadorLista) {
        this.identificadorLista = identificadorLista;
    }

    public String getAlbumSpotifyId() {
        return albumSpotifyId;
    }

    public void setAlbumSpotifyId(String albumSpotifyId) {
        this.albumSpotifyId = albumSpotifyId;
    }
	
	
	
	
	

}
