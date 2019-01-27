## What is this thing?

A simple command-line utility that scrapes a website for data it's interested in, and outputs the result as JSON. Also an interview task.

## How to build it?

Standard Maven build, no external dependencies.

## How to run it?

Like any Spring Boot application: `java -jar target/scraper-0.0.1-SNAPSHOT.jar` will launch the programme and the output is printed to standard out.

There are two optional parameters: `--vat` (default: 20) and `--url`, which can be used to determine the VAT rate used in the calculation (in percents) and the url of the product list page to scrape respectively.

## Notes

* The git commit history has deliberately not been rebased, to honour the fact this is a test.
* The tests use a mixture of approaches, just like above, this is deliberate.
* One big missing item is an integration test with a mock HTTP server.
* In several cases a compromise had to be struck between simplicity and demonstrating various concepts through the code. I tried to flag these up in the comments.
* One such decision was not to use Lombok for the value classes.

