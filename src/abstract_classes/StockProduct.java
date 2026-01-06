package abstract_classes;

import static service.UtilityFunctions.print;

/**Classe abstrada para aplicação de herança sobre modelos de produtos de estoque/prateleira da loja gerenciada pelo StoreCore. @author @MrErykCardoso.*/
public class StockProduct extends Product{
    private String code;
    private int inStock;

    public StockProduct(String name, String description, double price, double priceWithDiscount, Enterprise provider, String code, int inStock){
        super(name, description, price, priceWithDiscount, provider);

        this.code = code;
        this.inStock = inStock;
    }
    public StockProduct(String id, String name, String description, double price, double priceWithDiscount, Enterprise provider, String code, int inStock){
        super(id, name, description, price, priceWithDiscount, provider);

        this.code = code;
        this.inStock = inStock;
    }

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
        
        print("----- Estoque do produto -----");
        print("CÓDIGO: " + this.code + ";");
        print("QUANTIDADE EM ESTOQUE: " + this.inStock + ";");
    }
}
