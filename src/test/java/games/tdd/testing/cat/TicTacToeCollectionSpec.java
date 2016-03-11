package games.tdd.testing.cat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.net.UnknownHostException;
import static org.mockito.Mockito.*;
import org.jongo.MongoCollection;
import com.mongodb.MongoException; 


public class TicTacToeCollectionSpec {

	TicTacToeCollection collection;
	TicTacToeBean bean;
	MongoCollection mongoCollection;

	@Before
	public void before() throws UnknownHostException {
		collection = spy(new TicTacToeCollection());
		bean = new TicTacToeBean(3, 2, 1, 'Y');
		mongoCollection = mock(MongoCollection.class);
	}

	@Test
	public void whenInstantiatedThenMongoHasDbNameTicTacToe(){
		assertEquals("tic-tac-toe",collection.getMongoCollection().getDBCollection().getDB().getName());
	}

	@Test
	public void whenInstantiatedThenMongoCollectionHasNameCatGame(){
		assertEquals("catgame",collection.getMongoCollection().getName());
	}

	@Test
	public void whenSaveMoveThenInvokeMongoCollectionSave() {
		doReturn(mongoCollection).when(collection).getMongoCollection();
		collection.saveMove(bean);
		verify(mongoCollection, times(1)).save(bean);
	}

	@Test
	public void whenSaveMoveThenReturnTrue() {
		doReturn(mongoCollection).when(collection).getMongoCollection();
		assertTrue(collection.saveMove(bean)); 
	}

	@Test
	public void givenExceptionWhenSaveMoveThenReturnFalse() {
		doThrow(new MongoException("Bla"))
			.when(mongoCollection)
				.save(any(TicTacToeBean.class));
		doReturn(mongoCollection)
			.when(collection)
				.getMongoCollection();
		assertFalse(collection.saveMove(bean));
	}

	@Test
	public void whenDropThenInvokeMongoCollectionDrop() {
		doReturn(mongoCollection).when(collection)
			.getMongoCollection();
		collection.drop();
		verify(mongoCollection).drop();
	}
}
