if you would like to use your own csv file, please keep it in the bin folder.
if you wish to test the probing schemes, please follow the following steps:

1. Navigate to the bin folder from the command line interface
2. Ensure that the csv file of data starts from the second line
3. Use the following format when typing in the command line interface
   a) for testing a specific format:
      java MainTesting <TableSize> <scheme> <filename> <numberOfKeysToSearch>
      
      Please note:
      - TableSize is a prime number
      - when choosing scheme, choose from l - linear, q - quadratic, c - chaining
      - the file must be in the bin folder, and the should have a maximum of 999 records of power data
   b) For testing all formats:
      java MainTesting <prime1> <prime2> <prime3> <prime4> <prime5>
      
      please note:
      - the file being tested is cleaned_data.csv so the prime numbers need to be greater than 500 since linear and quadratic is being tested
