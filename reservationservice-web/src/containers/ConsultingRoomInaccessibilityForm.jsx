import React, { Component } from 'react';
import { Button, DatePicker, Form, Select, TimePicker } from 'antd';
import history from '../history'
import moment from 'moment'
import {
  getConsultingRoomInaccessibility,
  getConsultingRooms,
  saveConsultingRoomInaccessibility,
} from '../clients/ConsultingRoomClient'

const FormItem = Form.Item;
const Option = Select.Option;

class ConsultingRoomInaccessibilityForm extends Component {
  state = {
    confirmDirty: false,
    autoCompleteResult: [],
    // consultingRoom: null,
    consultingRooms: [],
  };

  componentWillMount = () => {
    getConsultingRooms().then((res) => this.setState({ consultingRooms: res }))
    let path = this.props.history.location.pathname.split('/')
    if (path[2] != 'new') {
      // getConsultingRoom(path[2]).then((res) => this.setState({ consultingRoom: res }))
      getConsultingRoomInaccessibility(path[2]).then((res) => this.setState({ consultingRoomInaccessibility: res }))

    }
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, fieldsValue) => {
      if (!err) {
        let consultingRoomInaccessibility = {
          // consultingRoom: this.state.consultingRoom,
          'consultingRoomId': fieldsValue.consultingRoomId,
          'date': fieldsValue.date,
          'startTime': fieldsValue.startTime.format('HH:MM:SS'),
          'endTime': fieldsValue.endTime.format('HH:MM:SS'),
        }
        saveConsultingRoomInaccessibility(consultingRoomInaccessibility)
          .then(() => {
              // this.props.onOk()
              this.props.form.resetFields();
              history.push('/consultingRoomInaccessibility');
            },
          )
      }
    });
  }

  handleCancel = () => {
    history.push('/consultingRoomInaccessibility')
  }

  format = 'HH:mm';
  dateFormat = 'YYYY-MM-DD';

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
        <h3>Dodaj niedostępność gabinetu</h3>

        <Form onSubmit={this.handleSubmit}>
          <FormItem
            {...formItemLayout}
            label={'Gabinet'}>
            {getFieldDecorator('consultingRoomId', {
              initialValue: this.state.consultingRoomInaccessibility ? this.state.consultingRoomInaccessibility.consultingRoom.id : null,
            })(
              <Select>
                {this.state.consultingRooms.map((cr) =>
                  <Option value={cr.id}
                          key={cr.id}>{cr.roomNumber + ' ' + cr.description}</Option>)}
              </Select>)}
          </FormItem>

          <FormItem
            {...formItemLayout}
            label="Data"
          >
            {getFieldDecorator('date', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: this.state.consultingRoomInaccessibility ? moment(this.state.consultingRoomInaccessibility.date, this.dateFormat) : null,
            })(<DatePicker
              format={this.dateFormat}/>)}
          </FormItem>


          <FormItem
            {...formItemLayout}
            label="Początek"
          >
            {getFieldDecorator('startTime', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: this.state.consultingRoomInaccessibility ? moment(this.state.consultingRoomInaccessibility.startTime, this.format) : null,
            })(<TimePicker
              format={this.format}/>)}
          </FormItem>


          <FormItem
            {...formItemLayout}
            label="Koniec"
          >
            {getFieldDecorator('endTime', {
              rules: [{
                required: true, message: 'Pole wymagane',
              }],
              initialValue: this.state.consultingRoomInaccessibility ? moment(this.state.consultingRoomInaccessibility.endTime, this.format) : null,
            })(
              <TimePicker format={this.format}/>,
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

const WrappedPatronForm = Form.create()(ConsultingRoomInaccessibilityForm);

export default WrappedPatronForm;
