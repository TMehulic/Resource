package ba.unsa.etf.rpr;

public class ResidenceInfo {

    private String adress;
    private Canton canton;
    private String county;

    public ResidenceInfo(String adress, Canton canton, String county) {
        this.adress = adress;
        this.canton = canton;
        this.county = county;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}
