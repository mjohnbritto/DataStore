# DataStore
A file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop. The data store can be exposed as a library to clients that can instantiate a class and work with the data store.
<h2> How to consume the DataStore library?</h2>

<p>First create a project and add the DataStore dependency, then you will be able instantiate and use the DataStore for your project usecase. For now the DataStore is available as a jar dependency only. Click here to download the <a href="datastore.jar">datastore.jar</a> <br/>
<p>Here's an example given below</p>
<pre>

/**
	 * Constructor initialize the DataStore with default storage location
	 */
DataStore myDataStore = new DataStore();// default location will be "C:\\Users\\Public\\Documents"

/**
	 * Constructor initialize the DataStore with given storage location
	 * 
	 * @param filePath
	 *            the storage location path
	 */
DataStore myDataStore = new DataStore(String filePath);//pass file location

/**
	 * 
	 * Method to create an element in the DataStore
	 * 
	 * @param key
	 *            The key of the element
	 * @param value
	 *            The value of the element
	 * @return status of the operation
	 */
myDataStore.create(String key, JSONObject value);

/**
	 * Method to create an element in the DataStore
	 * 
	 * @param key
	 *            The key of the element
	 * @param value
	 *            The value of the element
	 * @param timeToLive
	 *            Number of seconds after which the element is destroyed
	 * @return status of the operation
	 */
myDataStore.create(String key, JSONObject value, int timeToLive);

/**
	 * Method to read an element from the DataStore
	 * 
	 * @param key
	 *            The key of the element to read the element
	 * @return The value as type of JSONObject
	 */
myDataStore.read(String key)

/**
	 * Method to delete an element from the DataStore
	 * 
	 * @param key
	 *            The key of the element to read the element
	 * @return The status of the delete operation
	 */
myDataStore.delete(String key)
</pre>

<h3>Sample DataStore consumer</h3>
<p>The sample DataStore consumer application is here <a href="https://github.com/mjohnbritto/DataStoreConsumer">DataStoreConsumer</a></p>

<p>The sample response of DataStore consumer application given below</p>
<pre>
=============================================================
========================CREATE ==============================
=============================================================
Create operation successful
Operation failed due to key already available
Operation failed due to key already available
Create operation successful
Operation failed due to key length exceeded the limit(32chars)
====================AFTER WAIT===============
Create operation successful
Operation failed due to key already available
Operation failed due to key already available
Operation failed due to key already available
=============================================================
==========================READ===============================
=============================================================
{"firstName":"John","lastName":"Britto","address":"Chennai","age":"25"}
{"firstName":"John","lastName":"Britto","address":"Chennai"}
Operation failed due to key not available
Operation failed due to key length exceeded the limit(32chars)
====================AFTER WAIT===============
Operation failed due to key not available
{"firstName":"John","lastName":"Britto","address":"Chennai"}
=============================================================
========================DELETE ==============================
=============================================================
Operation failed due to key not available
Record deletion successful
Operation failed due to key not available
Operation failed due to key not available
Operation failed due to key length exceeded the limit(32chars)
</pre>

<p>Thank you!!!</p>
