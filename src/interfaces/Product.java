package interfaces;

public abstract class Product {
    //Atributes--------------------------------------
    private String name;
    private double price;
    private double discount;
    private String code;
    private String description;
    private Provider provider;
    private Maker maker;

    //get-set-----------------------------------------
    public String getName(){return this.name;}
    public double getPrice(){return this.price;}
    public double getDiscount(){return this.discount;}
    public String getCode(){return this.code;}
    public String getDescription(){return this.description;}
    public Provider getProvider(){return this.Provider;}
    public Maker getMaker(){return this.maker;}
    //------------------------------------------------
    public void setName(String name){this.name = name;}
    public void setPrice(double price){this.price = price;}
    public void setDiscount(double discount){this.discount = discount;}
    public void setCode(String code){this.code = code;}
    public void setDescription(String description){this.description = description;}
    public void setProvider(Provider provider){this.provider = provider;}
    public void setMaker(Maker maker){this.maker = maker;}
    
    //Constructor-------------------------------------
    public Product(){}
    public Product(String name, double price){
        this.name = name;
        this.price = price;
    }
    public Product(String name, double price, String code, String description, Provider provider, Maker maker){
        this.nome = name;
        this.price = price;
        this.code = code;
        this.description = description;
        this.provider = provider;
        this.maker = maker;
    }

    //Methods-----------------------------------------
    public void showAttributes(){
        System.out.println("\n\n-----Informações do Produto:-----");
        System.out.println("\nNome: " + this.nome + ";");
        System.out.println("Preço: " + this.price + "R$;");
        System.out.println("Preço com desconto: " + this.discount + "R$;");
        System.out.println("Código: " + this.code + ";");
        System.out.println("Descrição: " + this.description + ";");
        System.out.println("Fornecedor: " + this.provider + ";");
        System.out.println("Fabricante: " + this.maker + ";");
        System.out.println("\n------------------------------");
    }

    public void promotion(double percentage){
        if(percentage>50){
            throw new Exception("O valor de desconto não pode ser maior do que 50%;");
        }else{
            this.discount = this.price - (this.price*percentage/100);
        }
    }

}
