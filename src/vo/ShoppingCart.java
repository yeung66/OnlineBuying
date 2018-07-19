package vo;


import DAO.ShoppingCartDAO;
import java.util.Date;


/**
 * @ author: 杨浩麟
 * @ date: 2018/7/9 11:29
 */
public class ShoppingCart {
    private User user;
    private Product product;
    private int num;
    private Date startTime;

    public ShoppingCart(String uid, String pid, int num, Date startTime) {
        ShoppingCartDAO.setUser(user,uid);
        ShoppingCartDAO.setProduct(product,pid);
        this.num = num;
        this.startTime = startTime;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

}
