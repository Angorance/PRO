package dal.irepositories;

import dal.dalexception.DALException;
import dal.ientites.IDALBankaccountEntity;

import java.util.List;

/**
 * BankaccountRepository give the access methods for handle the bank account
 * into persistence
 *
 * @author Guillaume Zaretti
 * @version 1.2
 */
public interface IBankaccountRepository {
	
	/**
	 * Retreave a bankacount by this id
	 *
	 * @param id of the bankaccount
	 *
	 * @return IDALBankaccountEntity
	 *
	 * @throws DALException
	 */
	public IDALBankaccountEntity getBankaccount(int id) throws DALException;
	
	/**
	 * Retreave all bankaccount
	 *
	 * @return List<IDALBankaccountEntity>
	 *
	 * @throws DALException
	 */
	public List<IDALBankaccountEntity> getBankaccounts() throws DALException;
	
	/**
	 * Retreave the bankaccounts by client id
	 *
	 * @param id of the client
	 *
	 * @return List<IDALBankaccountEntity>
	 *
	 * @throws DALException
	 */
	public List<IDALBankaccountEntity> getBankAccoutsByClient(int id)
			throws DALException;
	
	/**
	 * Update a bankaccount
	 *
	 * @param bankaccount would'you update
	 *
	 * @throws DALException
	 */
	public void update(IDALBankaccountEntity bankaccount) throws DALException;
	
	/**
	 * Add a bankaccount
	 *
	 * @param bankaccount would'you add
	 *
	 * @throws DALException
	 */
	public Integer addBankaccount(IDALBankaccountEntity bankaccount)
			throws DALException;
	
	/**
	 * delete a bankaccount by bankaccount id
	 *
	 * @param id of the bankaccount
	 *
	 * @throws DALException
	 */
	public void delete(int id) throws DALException;
	
	/**
	 * Retreave the default bankaccount by client id
	 *
	 * @param id of the bankaccount
	 *
	 * @return IDALBankaccountEntity
	 *
	 * @throws DALException
	 */
	public IDALBankaccountEntity getDefaultBankAccountByClient(int id)
			throws DALException;
}
