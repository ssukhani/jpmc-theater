# jpmc-theater
A test movie theater app to access showtimes info and make reservation

# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions
* **Consider this to be your project! Feel free to make any changes**
* There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
* Implement the "New Requirements" below
* Keep it mind that code quality is very important
* Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
* You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features
* Customer can make a reservation for the movie
  * And, system can calculate the ticket fee for customer's reservation
* Theater have a following discount rules
  * 20% discount for the special movie
  * $3 discount for the movie showing 1st of the day
  * $2 discount for the movie showing 2nd of the day
* System can display movie schedule with simple text format

## New Requirements
* New discount rules; In addition to current rules
  * Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  * Any movies showing on 7th, you'll get 1$ discount
  * The discount amount applied only one if met multiple rules; biggest amount one
* We want to print the movie schedule with simple text & json format

## Refactoring and Enhancement - 
* Limiting the Theater Booking system offering the following user story 
  * As a customer I want to View list of Showings in the Theater for a day, from which I should be able to select a show 
    and book a ticket if the show is available.  
  * As a theater I want to keep track of all the Tickets available and sold, I also want to provide discount to ticket price. 
  * As a theater I want to interchange data in JSON format
* Based on above here are the limited feature - 
  * View current day's Showings in human readable format
  ** View filter Showing for a given Movie and/or starting within certain duration
  * Book ticket(s) for a Showing
  ** Cancellation API has not been exposed.
* Remodeled objects to domain and service companents keeping with OO principle of composition/aggregation and generalization and generics.
* Extracted interfaces from Service and Repo, to only expose the contracts. 
* Use DI principles to provide construct time behavior change, also helps with testing framworks like Mockito
* Use SpringBoot framework to provide inversion of control.
* Use JUnit and Mockito framework for testing.	
