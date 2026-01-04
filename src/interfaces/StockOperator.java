package interfaces;

/**Interface de padronização de métodos de usuários para verificar se o mesmo é habilitado para operar o estoque. @author @MrErykCardoso.*/
public interface StockOperator {
    abstract boolean canRegisterEntry();
    abstract boolean canRegisterExit();
}
