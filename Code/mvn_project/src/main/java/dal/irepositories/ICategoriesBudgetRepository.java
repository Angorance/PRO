package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALCategoriesbudgetEntity;

import java.util.List;

/**
 * ICategoriesBudgetRepository give the access methodes for handle the CategoriesBudget into persistence
 */
public interface ICategoriesBudgetRepository {
    /**
     * Retreave a CategoriesBudget by client id and budget id
     *
     * @param client_id id of the client
     * @param category_id id of the category
     * @return IDALCategoriesBudgetEntity the CategoriesBudget
     * @throws DALException
     */
    public IDALCategoriesbudgetEntity getCategoriesBudget(int client_id, int category_id) throws DALException;

    /**
     * Retreave all category budget
     *
     * @return List<IDALCategoriesBudgetEntity> the list of category budget
     * @throws DALException
     */
    public List<IDALCategoriesbudgetEntity> getCategoriesBudgets() throws DALException;

    /**
     * Update otransaction
     *
     * @param CategoriesBudget the debt would'you update
     * @throws DALException
     */
    public void update(IDALCategoriesbudgetEntity CategoriesBudget) throws DALException;

    /**
     * add a category budget
     *
     * @param categoriesBudget the category budget would'you add
     * @throws DALException
     */
    public void addCategoriesBudget(IDALCategoriesbudgetEntity categoriesBudget) throws DALException;

    /**
     * delete a CategoriesBudget
     * @param categoriesBudget  the category budget would'you delete
     * @throws DALException
     */
    public void delete(IDALCategoriesbudgetEntity categoriesBudget) throws DALException;


    /**
     * Retreave the CategoriesBudget by client id
     *
     * @param client_id id of the client
     * @return IDALCategoriesBudgetEntity the CategoriesBudget
     * @throws DALException
     */
    public List<IDALCategoriesbudgetEntity> getCategoriesBudgetByBudget(int budget_id) throws DALException;

    /**
     * Retreave a CategoriesBudget by budget id
     *
     * @param category_id id of the budget
     * @return IDALCategoriesBudgetEntity the CategoriesBudget
     * @throws DALException
     */
    public List<IDALCategoriesbudgetEntity> getCategoriesBudgetByCategory(int category_id) throws DALException;

}