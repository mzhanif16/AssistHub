package MyAssistHub.App.model;


public class Booking {
    private long id,priceService;
    private String serviceName,noteBooking;
    private String userName;
    private int status,totalUnit;
    private String hourBooking;
    private String dateBooking;
    private String category;

    public Booking() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHourBooking() {
        return hourBooking;
    }

    public void setHourBooking(String hourBooking) {
        this.hourBooking = hourBooking;
    }

    public String getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(String dateBooking) {
        this.dateBooking = dateBooking;
    }

    public long getPriceService() {
        return priceService;
    }

    public void setPriceService(long priceService) {
        this.priceService = priceService;
    }

    public int getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(int totalUnit) {
        this.totalUnit = totalUnit;
    }

    public String getNoteBooking() {
        return noteBooking;
    }

    public void setNoteBooking(String noteBooking) {
        this.noteBooking = noteBooking;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
