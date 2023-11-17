# Restaurant Management (Java)

This Java-based restaurant management system offers a suite of features to manage various aspects of a restaurant's operations, including orders, inventory, menus, etc. The application uses text files as a data storage mechanism for persisting information between sessions.

## Features

- **Order Management:** Create, process, and manage customer orders seamlessly.
- **Menu Management:** Update and maintain the restaurant menu items and prices effortlessly.
- **Inventory Control:** Track available ingredients and supplies in real-time.
- **Sales Management:** Track and manage expenses and sales of restaurant. 

## Usage

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/syedasrar00/restaurantManagement.git

2. **Compile and Run:**
    ```bash
    cd restaurantManagement/src
    java Main

3. **Data Storage:**
    - The application stores data in text files (accounts.txt, ingredients.txt, recipie.txt) within the project directory.
    - Ensure these files have appropriate read/write permissions.

## Data Persistence

  - The application saves data (accounts, menu items, inventory) to text files upon closing to retain information between sessions.
  - Data is loaded from these files when the application starts.
## Contributing
Contributions are welcome! Feel free to open issues or submit pull requests.

## License
This project is licensed under the MIT License.
