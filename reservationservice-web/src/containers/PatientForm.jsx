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
        //TODO
      }
    });
  }

  handleCancel = () => {
    if (history.location.pathname === '/patron/new') {
      history.push('/patients')
    }
    else {
      history.push('/patient/' + _.get(this.props, 'data.id'))
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
            {getFieldDecorator('name', {
              rules: [{
                // required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'data.name'),
            })
            (
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Gatunek"
          >
            {getFieldDecorator('species', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'data.breed.species'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Rasa"
          >
            {getFieldDecorator('breed', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: _.get(this.props, 'data.breed.name'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Umaszczenie"
          >
            {getFieldDecorator('coat', {
              rules: [],
              initialValue: _.get(this.props, 'data.coat'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Zanki szczególne"
          >
            {getFieldDecorator('specialCharacters', {
              rules: [],
              initialValue: _.get(this.props, 'data.specialCharacters'),
            })(
              <Input/>,
            )}
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="Płeć"
          >
            {getFieldDecorator('sex', {
              rules: [],
              initialValue: _.get(this.props, 'data.sex'),
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
