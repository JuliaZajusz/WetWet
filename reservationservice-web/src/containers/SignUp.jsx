import React, { Component } from 'react'
import { Button, Form, Icon, Input } from 'antd'
import '../css/SignUp.css'
import history from '../history'
import { createAccount } from '../clients/AuthorizationClient'

const FormItem = Form.Item;

class SignUp extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        createAccount({
          credentials: {
            login: values.login,
            password: values.password,
          },
          employee: {
            userName: values.userName,
            firstName: values.firstName,
            lastName: values.lastName,
            positionId: values.positionId,
          },
        }).then(() => history.push('/login'))
      }
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSubmit} className="login-form login-form--sign-up margin-md">
        <FormItem>
          {getFieldDecorator('login', {
            rules: [{ required: true, message: 'Podaj login!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Login"/>,
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Podaj hasło!' }],
          })(
            <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }}/>} type="password"
                   placeholder="Hasło"/>,
          )}
        </FormItem>

        <FormItem>
          {getFieldDecorator('userName', {
            rules: [{ required: true, message: 'Podaj username //do usunięcia!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Username"/>,
          )}
        </FormItem>

        <FormItem>
          {getFieldDecorator('firstName', {
            rules: [{ required: true, message: 'Podaj imię!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Imię"/>,
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('lastName', {
            rules: [{ required: true, message: 'Podaj nazwisko!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Nazwisko"/>,
          )}
        </FormItem>

        <FormItem>
          {getFieldDecorator('positionId', {
            rules: [{ required: true, message: 'Podaj pozycję  //to ma być dropdown!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Pozycja"/>,
          )}
        </FormItem>

        <FormItem>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Utwórz
          </Button>
        </FormItem>
      </Form>
    );
  }

}

const WrappedSignUpForm = Form.create()(SignUp);


export default WrappedSignUpForm
