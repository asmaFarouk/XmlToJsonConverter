# XML to JSON Converter Project

This project to convert xml file to json then retrieve the value of the
attributes from the XML response

## Prerequisites

- Java 8 or higher
- Maven (for building and managing dependencies)

## Dependencies
### [Jackson Library]
- Used for XML to JSON conversion.

### [JsonPath Library]
- Used for extracting specific data from JSON responses.

### [Json Smart Library]
- Used for working with JSON data.

## Getting Started

1. Clone the repository:

    ```bash
    git clone https://github.com/asmaFarouk/XmlToJsonConverter.git
    ```

2. Navigate to the project directory:

    ```bash
    cd XmlToJsonConverter
    ```

3. Build the project using Maven:

    ```bash
    mvn clean install
    ```

## Running the Application

You can run the application using the following command:

```bash
mvn exec:java
 ```

## Test Output

After running the program, check the output at the terminal. 
The following values should appear:


- **DecisionFlowId=** 12
- **SCORE=** 301
