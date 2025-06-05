package com.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person {
    private String ticketHolder;
    private Event event;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    // Default constructor
    public Person() {
        this.ticketHolder = "";
        this.event = null;
        this.firstName = "";
        this.lastName = "";
        this.birthDate = null;
    }

    // Constructor with all parameters
    public Person(String ticketHolder, Event event, String firstName, String lastName, LocalDate birthDate) {
        this.ticketHolder = ticketHolder;
        this.event = event;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Constructor without event (for general person creation)
    public Person(String ticketHolder, String firstName, String lastName, LocalDate birthDate) {
        this.ticketHolder = ticketHolder;
        this.event = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Constructor from User (for converting User to Person for ticket purposes)
  

    // Getters
    public String getTicketHolder() {
        return ticketHolder;
    }

    public Event getEvent() {
        return event;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    // Setters
    public void setTicketHolder(String ticketHolder) {
        this.ticketHolder = ticketHolder;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get the full name of the person
     * @return first name + last name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Calculate age based on birth date
     * @return age in years, or -1 if birth date is not set
     */
    public int getAge() {
        if (birthDate == null) {
            return -1;
        }
        return LocalDate.now().getYear() - birthDate.getYear();
    }

    /**
     * Get birth date as formatted string
     * @return birth date as string in yyyy-MM-dd format
     */
    public String getBirthDateAsString() {
        if (birthDate == null) {
            return "";
        }
        return birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Check if person is associated with a specific event
     * @param event the event to check
     * @return true if person is associated with the event, false otherwise
     */
    public boolean isAssociatedWithEvent(Event event) {
        return this.event != null && this.event.equals(event);
    }

    /**
     * Check if person is the ticket holder (matches their name/username)
     * @param identifier the name or username to check
     * @return true if person is the ticket holder, false otherwise
     */
    public boolean isTicketHolder(String identifier) {
        return ticketHolder.equalsIgnoreCase(identifier) ||
               getFullName().equalsIgnoreCase(identifier);
    }

    /**
     * Check if person is an adult (18 or older)
     * @return true if adult, false if minor or age unknown
     */
    public boolean isAdult() {
        int age = getAge();
        return age >= 18;
    }

    /**
     * Check if person is a minor (under 18)
     * @return true if minor, false if adult or age unknown
     */
    public boolean isMinor() {
        int age = getAge();
        return age >= 0 && age < 18;
    }

    /**
     * Validate that person has all required information
     * @return true if all required fields are filled, false otherwise
     */
    public boolean isValidPerson() {
        return ticketHolder != null && !ticketHolder.isEmpty() &&
               firstName != null && !firstName.isEmpty() &&
               lastName != null && !lastName.isEmpty() &&
               birthDate != null;
    }

    /**
     * Clear event association
     */
    public void clearEventAssociation() {
        this.event = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");
        sb.append("ticketHolder='").append(ticketHolder).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate=").append(birthDate);
        if (event != null) {
            sb.append(", associatedEvent='").append(event.getName()).append('\'');
        }
        sb.append(", age=").append(getAge());
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Objects.equals(ticketHolder, person.ticketHolder) &&
               Objects.equals(firstName, person.firstName) &&
               Objects.equals(lastName, person.lastName) &&
               Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketHolder, firstName, lastName, birthDate);
    }

    /**
     * Create a copy of this person
     * @return a new Person object with the same data
     */
    public Person copy() {
        return new Person(this.ticketHolder, this.event, this.firstName, this.lastName, this.birthDate);
    }

    /**
     * Compare persons by name (for sorting)
     * @param other the other person to compare to
     * @return negative if this person comes before other, positive if after, 0 if equal
     */
    public int compareByName(Person other) {
        int lastNameComparison = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastNameComparison != 0) {
            return lastNameComparison;
        }
        return this.firstName.compareToIgnoreCase(other.firstName);
    }

    /**
     * Compare persons by age (for sorting)
     * @param other the other person to compare to
     * @return negative if this person is younger, positive if older, 0 if same age
     */
    public int compareByAge(Person other) {
        return Integer.compare(this.getAge(), other.getAge());
    }
}
