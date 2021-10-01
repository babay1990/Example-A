public class Order implements Comparable<Order> {

    private int event_id;
    private int order_id;
    private int item_id;
    private int count;
    private int return_count;
    private String status;

    public Order(int event_id, int order_id, int item_id, int count, int return_count, String status) {
        this.event_id = event_id;
        this.order_id = order_id;
        this.item_id = item_id;
        this.count = count;
        this.return_count = return_count;
        this.status = status;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getReturn_count() {
        return return_count;
    }

    public void setReturn_count(int return_count) {
        this.return_count = return_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Order order) {
        if(getEvent_id() > order.getEvent_id()){
            return -1;
        }
        if(getEvent_id() < order.getEvent_id()){
            return 1;
        }
        return 0;
    }
}
