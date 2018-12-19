import React, { Component } from 'react';
import { Button, Form, Input, Select } from 'antd';
import { addPatron, updatePatron } from '../clients/PatronClient'
import history from '../history'
import _ from 'lodash'
import WrappedAddressForm from './AddressForm'

const FormItem = Form.Item;
const Option = Select.Option;

class PatronForm extends Component {

  constructor(props) {
    super();
    this.state = {
      confirmDirty: false,
      autoCompleteResult: [],
      pets: _.get(props, 'data.pets') || [],
      addresses: _.get(props, 'data.addresses') || [],
    };
  }
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        if (history.location.pathname === '/patron/new') {
          addPatron(values).then((res) => {
              this.props.onSubmitSuccess(res)
            },
          )
        }
        else {
          updatePatron({ ...values, id: this.props.data.id }).then((res) => {
              this.props.onSubmitSuccess(res)
            },
          )
        }
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
      <div className={'margin-md'}>
        <h3>Dodaj opiekuna</h3>

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
          {this.state.pets.map((pet) => {
            return <FormItem label={pet.name}>
              <Button onClick={() => this.handleDeletePet(pet.id)}>Usuń</Button>
            </FormItem>
          })}
          {/*<FormItem>*/}
          <div>
            Zwierzęta:
            {/*{this.state.pets.map((pet) => {*/}
            {/*return <div>*/}
            {/*{pet.name}*/}
            {/*<Button onClick={()=> this.handleDeletePet(pet.id)}>Usuń</Button>*/}
            {/*</div>*/}
            {/*})}*/}
          </div>
          <FormItem>
            <Select
              onChange={(id) => this.handleAddPet(id)}
            >
              <Option value="rmb">RMB</Option>
              <Option value="dollar">Dollar</Option>
            </Select>
          </FormItem>
          <div>
            Adresy:
            {this.state.addresses.map((address) => {
              return <div>
                {address.street && address.street.name + ' '}

                {address.street && address.houseAppartmentNumber + ', '}
                {address.city && address.city.name + ' '}
                {!address.street && address.houseAppartmentNumber}

                <WrappedAddressForm address={address}/>
              </div>
            })}
          </div>
          {/*</FormItem>*/}
          <FormItem {...tailFormItemLayout}>
            <Button type="secondary" htmlType="button" onClick={() => this.handleCancel()}>Anuluj</Button>
            <Button type="primary" htmlType="submit">Zapisz</Button>
          </FormItem>
        </Form>

      </div>
    )
  }

  handleDeletePet(id) {
    let newPets = this.state.pets.filter((pet) => pet.id !== id)
    this.setState({ pets: newPets })
  }

  handleAddPet(id) {
    console.log(id)
  }
}

const WrappedPatronForm = Form.create()(PatronForm);

export default WrappedPatronForm;
