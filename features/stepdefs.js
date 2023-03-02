const assert = require('assert');
const { Given, When, Then } = require('@cucumber/cucumber');
//import chai
const chai = require('chai');
const chaiHttp = require('chai-http');
const app = require('../app');
const should = chai.should();
chai.use(chaiHttp);

Given('a student with id {string}', function (id) {
    this.id = id;
});

When('a student requests to get the todo with id {string}', async function (id) {
    const response = await this.request.get(`/todos/${id}`);
    this.response = response;
});

Then('the student should get the todo with id {string}', function (id) {
    this.response.body.should.have.property('id').eql(id);
});

Then('the student should get the todo with title {string}', function (title) {
    this.response.body.should.have.property('title').eql(title);
});

//This is all bullshit code generated be ChatGPT


