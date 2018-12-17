import React, { Component } from 'react'
import { Button, Form, Icon, Input } from 'antd'
import '../css/SignIn.css'
import history from '../history'
import { logIn } from '../clients/AuthorizationClient'

const FormItem = Form.Item;

class SignIn extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        logIn({ login: values.login, password: values.password })
          .then(() => {
              history.push('/patients')
            },
          )
      }
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSubmit} className="login-form login-form--sign-in ">
        <FormItem>
          {getFieldDecorator('login', {
            rules: [{ required: true, message: 'Please input your username!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Email"/>,
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Please input your Password!' }],
          })(
            <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }}/>} type="password"
                   placeholder="Password"/>,
          )}
        </FormItem>
        <FormItem>
          <a className="login-form-forgot" href="/resetPassword">Zmień hasło</a>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Zaloguj się
          </Button>
        </FormItem>
      </Form>
    );
  }

}

const WrappedSignInForm = Form.create()(SignIn);

export default WrappedSignInForm
