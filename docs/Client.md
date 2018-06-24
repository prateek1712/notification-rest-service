**Get User**
----
  Returns information about a single client in JSON format

* **URL:** <t><t> `/clients?id={id}`

* **Method:**
 `GET`
  
*  **URL Params**

   **Required:**
 
   `id=[integer]`

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `{ id : 12, name : "Michael Bloom" }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "Client with ClientID 5 doesn't exist" }`

  OR

  * **Code:** 400 BAD REQUEST <br />
    **Content:** `{ error : "You are unauthorized to make this request." }`

* **Sample Call:**

  ```javascript

  ```