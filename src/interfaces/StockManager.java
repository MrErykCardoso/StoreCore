package interfaces;

/**Interface de padronização de métodos de usuário para verificar se o mesmo é habilitao para gerenciar o estoque. @author @MrErykCardoso.*/
public interface StockManager {
    abstract boolean canRegisterProduct();
    abstract boolean canEditProduct();
    abstract boolean canDeleteProduct();
}
