import React, { Component } from 'react'
import { Button, Form, Icon, Input } from 'antd'
import '../css/SignIn.css'
import history from '../history'
import { resetPassword } from '../clients/AuthorizationClient'

const FormItem = Form.Item;

class SignIn extends Component {
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        resetPassword({ login: values.login, password: values.password, newPassword: values.newPassword })
          .then(() => {
              history.push('/login')
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
            rules: [{ required: true, message: 'Podaj login!' }],
          })(
            <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }}/>} placeholder="Login"/>,
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Podaj stare hasło!' }],
          })(
            <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }}/>} type="password"
                   placeholder="Hasło"/>,
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Podaj nowe hasło!' }],
          })(
            <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }}/>} type="newPassword"
                   placeholder="Nowe hasło"/>,
          )}
        </FormItem>
        <FormItem>
          <a className="login-form-forgot" href="/login">Zaloguj się</a>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Zmień hasło
          </Button>
        </FormItem>
      </Form>
    );
  }

}

const WrappedSignInForm = Form.create()(SignIn);

export default WrappedSignInForm
