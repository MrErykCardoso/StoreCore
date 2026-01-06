package abstract_classes;

public class StockProduct extends Product{
    private String code;
    private int inStock;

    public String getCode(){
        return this.code;
    }
    public int getInStock(){
        return this.inStock;
    }

    public void setInStock(int inStock){
        this.inStock = inStock;
    }
    public void setCode(String code){
        this.code = code;
    }

    @Override
    public void printInf(){
        super.printInf();

        //TODO
    }
}
