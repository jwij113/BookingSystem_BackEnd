package com.dominicon.booking.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String middleName;
  private String lastName;

  protected Customer() {}

  public Customer(String firstName, String middleName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', middleName='%s', lastName='%s']",
        id, firstName, middleName, lastName);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
  
  public String getMiddleName() {
	    return middleName;
  }
}