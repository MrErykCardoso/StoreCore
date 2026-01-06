package abstract_classes;

import static service.UtilityFunctions.print;

/**Classe abstrata para aplicação de herança sobre modelos de serviços fornecidos pela loja administrada pelo StoreCore. @author @MrErykCardoso.*/
public abstract class Service extends Product{
    private String startDate;
    private String endDate;

    public Service(String name, String description, double price, double priceWithDiscount, Enterprise provider, String startDate, String endDate){
        super(name, description, price, priceWithDiscount, provider);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Service(String id, String name, String description, double price, double priceWithDiscount, Enterprise provider, String startDate, String endDate){
        super(id, name, description, price, priceWithDiscount, provider);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate(){
        return this.startDate;
    }
    public String getEndDate(){
        return this.endDate;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    @Override
    public void printInf(){
        super.printInf();
        
        print("----- Informações do serviço -----");
        print("DATA DE INÍCIO: " + this.startDate + ";");
        print("DATA DE TÉRMINO: " + this.endDate + ";");
    }
}