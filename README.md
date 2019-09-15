# DataStore
A file-based key-value data store that supports the basic CRD (create, read, and delete) operations. This data store is meant to be used as a local storage for one single process on one laptop. The data store can be exposed as a library to clients that can instantiate a class and work with the data store.
<h2> How to consume the DataStore library?</h2>

<p>First create a project and add the DataStore dependency, then you will be able instantiate and use the DataStore for your project usecase. For now the DataStore is available as a jar dependency only. Click here to download the <a href="datastore.jar">datastore.jar</a> <br/>
<p>Here's an example given below</p>
<pre>
//To Instantiate the DataStore library
DataStore myDataStore = new DataStore("C:\\Users\\John\\Documents\\DataStore");//pass file location
//To Create an element
myDataStore.create("1", jsonObject,10);
//To Read the element
myDataStore.read("1")
//To Delete an element
myDataStore.delete("1")
</pre>

<p>The sample DataStore consumer application is here <a href="DataStore/src/com/datastore/main/DataStoreConsumer.java">Click</a> here</p>

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
