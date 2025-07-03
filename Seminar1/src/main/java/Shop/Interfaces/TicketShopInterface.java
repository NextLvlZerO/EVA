package Shop.Interfaces;

public interface TicketShopInterface {
    public TicketServiceInterface getTicketService();

    public CustomerServiceInterface getCustomerService();

    public EventServiceInterface getEventService();

    public LogServiceInterface getLogService();

    public Object convertString(String input) throws Exception;
}

