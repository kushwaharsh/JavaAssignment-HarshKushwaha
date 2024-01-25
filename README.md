
# SunbaseData-Assignment

## Title: Customer Management Web Application

This project is a simple CRUD application for managing customer information, implemented using MySQL for the database, JSP/Servlet or Spring Boot for the backend, and HTML/CSS/JS for the frontend. The application provides basic functionality such as creating, updating, deleting, and retrieving customer records. JWT authentication is implemented for securing the APIs.

## Application Features

#### Login Screen:
Users can log in using their credentials. The API will authenticate the user and return a bearer token, which will be stored for further API calls.
#### Customer List Screen:
After successful login, users can view the list of existing customers. The API call will retrieve the customer data and display it in a table format.
#### Add New Customer:
Users can create a new customer by filling out a form with the required details and submitting it. The API call will add the new customer to the database.
#### Delete Customer:
Users can select a customer from the list and choose to delete them. The API call will remove the selected customer from the database.
#### Update Customer:
Users can select a customer from the list, update their details in a form, and submit the changes. The API call will update the customer information in the database.
#### Search Feature:
Users can search for specific customers by entering relevant keywords or criteria. The application will make an API call to retrieve and display the search results, allowing users to quickly find the desired customer information.
#### Sync Feature:
The application includes a sync feature that enables users to update their local customer list with the latest data from the server. This ensures that the displayed information is always up-to-date. The sync feature can be triggered manually by the user.
## Application Workflow and Endpoints

#### Login
- Path: https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp
- Method: POST
- Body: JSON object containing login credentials
- Response: Returns a bearer token for further API calls

#### Fetch data from server
- Path: https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp
- Method: GET
- Parameters: cmd=get_customer_list
- Header: Authorization with Bearer token obtained from the Authentication API
- Response: Returns a list of customer objects in JSON format

#### Index Page:
- Endpoint:/index
- Method: GET

#### Customer Login Page:
- Endpoint: /CustomerLogin
- Method: GET

#### List Customers:
- Endpoint: /students
- Method: GET

#### Create Customer Form:
- Endpoint: /students/new
- Method: GET

#### Save Customer:
- Endpoint: /students
- Method: POST

#### Edit Customer Form:
- Endpoint: /students/edit/{id}
- Method: GET

#### Update Customer:
- Endpoint: /students/{id}
- Method: POST

#### Delete Customer:
- Endpoint: /students/{id}
- Method: GET

#### Search Customers:
- Endpoint: /search
- Method: GET

#### Query Parameters:
- field (e.g., "first_name", "last_name", "email", etc.)
- query (search query)

#### Sync Data:
- Endpoint: /sync
- Method: GET
- Response: 200 OK: Sync successful500 Internal Server Error: Error in syncing data
## How to run the application

- Clone the repository: git clone <https://github.com/kushwaharsh/JavaAssignment-HarshKushwaha.git>
- Set up the MySQL database and update the application.properties file with the database configuration.
- Run the backend application (MainApplication.java file).
- Open the index.html file in a web browser for the frontend.
- Login with valid credentials to access the customer list and add/update/delete customers.
## How to run the application

- Clone the repository: git clone <https://github.com/kushwaharsh/JavaAssignment-HarshKushwaha.git>
- Set up the MySQL database and update the application.properties file with the database configuration.
- Run the backend application (MainApplication.java file).
- Open the index.html file in a web browser for the frontend.
- Login with valid credentials to access the customer list and add/update/delete customers.
## Additional Notes

- The project structure, code, and comments are designed for readability and maintainability.
- All sensitive information, including login credentials, is securely managed and not hardcoded.
