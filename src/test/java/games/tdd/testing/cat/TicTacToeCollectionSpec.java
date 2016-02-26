package games.tdd.testing.cat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.net.UnknownHostException;
import static org.mockito.Mockito.*;
import org.jongo.MongoCollection;



public class TicTacToeCollectionSpec {

	private TicTacToeCollection collection;

	@Before
	public void before()  throws UnknownHostException {
		collection = spy(new TicTacToeCollection());
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
		TicTacToeBean bean = new TicTacToeBean(3, 2, 1, 'Y');
		MongoCollection mongoCollection = mock(MongoCollection.class);
		doReturn(mongoCollection).when(collection).getMongoCollection();
		collection.saveMove(bean);
		verify(mongoCollection, times(1)).save(bean);
	}
}
