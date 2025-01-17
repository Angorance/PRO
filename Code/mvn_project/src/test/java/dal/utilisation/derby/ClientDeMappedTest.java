//package dal.utilisation.derby;
//import bll.mappers.DAL.DALClientMapper;
//import bll.model.ClientModel;
//import dal.dalexception.DALException;
//import dal.irepositories.IClientRepository;
//import dal.repositories.pgsql.ClientPgRepository;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClientDeMappedTest {
//
//    public IClientRepository clientRepository;
//
//    public List<ClientModel> listClients =  new ArrayList();
//
//
//    @Before
//    public void setUp() throws Exception {
//        ClientModel clientOne = new ClientModel();
//        ClientModel clientTwo = new ClientModel();
//        ClientModel clientTree = new ClientModel();
//        ClientModel clientFour = new ClientModel();
//
//
//
//        clientOne.setUsername("One");
//        clientOne.setEmail("One");
//        clientOne.setPassword("One");
//        clientOne.setActivated(true);
//        clientOne.setKey("One");
//        clientOne.setSalt("One");
//
//        clientTwo.setUsername("Two");
//        clientTwo.setEmail("Two");
//        clientTwo.setPassword("Two");
//        clientTwo.setActivated(true);
//        clientTwo.setKey("Two");
//        clientTwo.setSalt("Two");
//
//        clientTree.setUsername("Tree");
//        clientTree.setEmail("Tree");
//        clientTree.setPassword("Tree");
//        clientTree.setActivated(true);
//        clientTree.setKey("Tree");
//        clientTree.setSalt("Tree");
//
//        clientFour.setUsername("One");
//        clientFour.setEmail("One");
//        clientFour.setPassword("One");
//        clientFour.setActivated(true);
//        clientFour.setKey("One");
//        clientFour.setSalt("One");
//
//        listClients.formReturn(clientOne);
//        listClients.formReturn(clientTwo);
//        listClients.formReturn(clientTree);
//        listClients.formReturn(clientFour);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//    }
//
//    @Test
//    public void getClient() throws DALException {
//        clientRepository = new ClientPgRepository();
//        ClientModel client = DALClientMapper.toBo(clientRepository.getClient(1));
//
//        Assert.assertNull(client);
//    }
//
//    @Test
//    public void addClient() throws DALException {
//        clientRepository = new ClientPgRepository();
//        clientRepository.addClient(DALClientMapper.toDboPG(listClients.get(1)));
//        ClientModel client = DALClientMapper.toBo(clientRepository.getClient(0));
//        Assert.assertNotNull(client);
//    }
//
//    @Test
//    public void getClients() {
//    }
//
//    @Test
//    public void getClients1() {
//    }
//
//    @Test
//    public void update() throws DALException {
//        clientRepository = new ClientPgRepository();
//        ClientModel client = DALClientMapper.toBo(clientRepository.getClient(0));
//        client.setUsername("updated");
//        clientRepository.update(DALClientMapper.toDboPG(client));
//        client = DALClientMapper.toBo(clientRepository.getClient(0));
//        Assert.assertNull(client);
//
//    }
//
//    @Test
//    public void delete() {
//
//    }
//}
