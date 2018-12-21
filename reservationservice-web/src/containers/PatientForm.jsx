import React, { Component } from 'react';
import { Button, Form, Input } from 'antd';
import history from '../history'
import _ from 'lodash'

const FormItem = Form.Item;

class PatientForm extends Component {
  state = {
    confirmDirty: false,
    autoCompleteResult: [],
  };

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        // if (history.location.pathname === '/patron/new') {
        //   addPatron(values).then((res) => {
        //       this.props.onSubmitSuccess(res)
        //     },
        //   )
        // }
        // else {
        //   updatePatron({ ...values, id: this.props.data.id }).then((res) => {
        //       this.props.onSubmitSuccess(res)
        //     },
        //   )
        // }
      }
    });
  }

  handleCancel = () => {
    if (history.location.pathname === '/patron/new') {
      history.push('/patrons')
    }
    else {
      history.push('/patron/' + _.get(this.props, 'data.id'))
    }
  }

  render() {
    const { getFieldDecorator } = this.props.form;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };

//TODO
    return (
      <div className={'margin-md'}>
        <h3>Dodaj lub edytuj pacjenta</h3>

        <Form onSubmit={this.handleSubmit}>
          <FormItem {...formItemLayout} label="Imię">
            {getFieldDecorator('firstName', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'data.firstName'),
            })
            (
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Nazwisko"
          >
            {getFieldDecorator('lastName', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'data.lastName'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="E-mail"
          >
            {getFieldDecorator('email', {
              rules: [{
                type: 'email', message: 'Niepoprawny email!',
              }],
              initialValue: _.get(this.props, 'data.email'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Telefon"
          >
            {getFieldDecorator('phone', {
              rules: [],
              initialValue: _.get(this.props, 'data.phone'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem {...tailFormItemLayout}>
            <Button type="secondary" htmlType="button" onClick={() => this.handleCancel()}>Anuluj</Button>
            <Button type="primary" htmlType="submit">Zapisz</Button>
          </FormItem>
        </Form>

      </div>
    )
  }
}

const WrappedPatronForm = Form.create()(PatientForm);

export default WrappedPatronForm;
