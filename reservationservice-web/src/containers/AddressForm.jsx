import React, { Component } from 'react';
import { Button, Form, Input } from 'antd';
import { addPatron } from '../clients/PatronClient'
import history from '../history'
import _ from 'lodash'

const FormItem = Form.Item;

class AddressForm extends Component {

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        addPatron(values).then((res) => {
            this.props.onSubmitSuccess(res)
          },
        )
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

    return (
      <div className={'margin-md border-top border-bottom pt-24'}>
        <Form onSubmit={this.handleSubmit}>
          <FormItem {...formItemLayout} label="Ulica">
            {getFieldDecorator('street', {
              rules: [{
                // required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'address.street.name'),
            })
            (
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Numer domu/mieszkania"
          >
            {getFieldDecorator('houseAppartmentNumber', {
              rules: [{
                // required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'address.houseAppartmentNumber'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Miejscowość"
          >
            {getFieldDecorator('city', {
              rules: [{
                // type: 'email', message: 'Niepoprawny email!',
              }],
              initialValue: _.get(this.props, 'address.city.name'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem {...tailFormItemLayout}>
            <Button type="secondary" htmlType="button" onClick={() => this.handleCancel()}>Anuluj</Button>
            <Button shape="circle" icon="plus" htmlType="submit"/>
          </FormItem>
        </Form>

      </div>
    )
  }
}

const WrappedAddressForm = Form.create()(AddressForm);

export default WrappedAddressForm;
