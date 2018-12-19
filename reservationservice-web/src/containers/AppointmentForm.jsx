import { Button, DatePicker, Form, Input, Select, TimePicker } from 'antd';
import React, { Component } from 'react';
import moment from 'moment';
import { saveAppointments } from '../clients/AppointmentClient';
import _ from 'lodash'

const Option = Select.Option;
const FormItem = Form.Item;
const {MonthPicker, RangePicker} = DatePicker;

class AppointmentForm extends Component {
  constructor(props) {
    super();
    this.state = {
      consultingRooms: [],
    }
  }

  // componentWillMount() {
  //   console.log("mount")
  // }
    format = 'HH:mm';
    dateFormat = 'YYYY-MM-DD';
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.props.form.getFieldsValue())
        this.props.form.validateFields((err, fieldsValue) => {
            if (err) {
                return;
            }
          let appointment = {
            'id': _.get(this.props, 'appointment.id'),
            'title': fieldsValue.title,
            'description': fieldsValue.description,
            'cost': null,
            'date': fieldsValue.date,
            'startTime': fieldsValue.startTime.format('HH:MM:SS'),
            'endTime': fieldsValue.endTime.format('HH:MM:SS'),
            addressPointId: 1,
            consultingRoomId: fieldsValue.consultingRoomId,
          }
          saveAppointments(appointment)
            .then(() => {
                this.props.onOk()
              },
            )
        });
    }

    render() {
        const {getFieldDecorator} = this.props.form;
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 8},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 16},
            },
        };
        return (
            <Form onSubmit={this.handleSubmit}>
              <FormItem
                {...formItemLayout}
                label="Tytuł"
              >
                {getFieldDecorator('title', {
                  rules: [{
                    required: true, message: 'Pole wymagane',
                  }],
                  initialValue: this.props.appointment ? this.props.appointment.title : null,
                })(
                  <Input/>,
                )}
              </FormItem>
              <FormItem
                {...formItemLayout}
                label="Opis"
              >
                {getFieldDecorator('description', {
                  initialValue: this.props.appointment ? this.props.appointment.description : null,
                })(
                  <Input/>,
                )}
              </FormItem>
                <FormItem
                    {...formItemLayout}
                    label="Data wizyty"
                >
                  {getFieldDecorator('date', {
                    rules: [{
                      required: true, message: 'Pole wymagane',
                    }],
                    initialValue: this.props.appointment ? moment(this.props.appointment.date, this.dateFormat) : moment(this.props.slotInfo.start, this.dateFormat),
                  })(<DatePicker
                    format={this.dateFormat}/>)}
                </FormItem>


                <FormItem
                    {...formItemLayout}
                    label="Początek wizyty"
                >
                  {getFieldDecorator('startTime', {
                    rules: [{
                      required: true, message: 'Pole wymagane',
                    }],
                    initialValue: this.props.appointment ? moment(this.props.appointment.startTime, this.format) : moment(this.props.slotInfo.start, this.format),
                  })(<TimePicker
                    format={this.format}/>)}
                </FormItem>


                <FormItem
                    {...formItemLayout}
                    label="Koniec wizyty"
                >
                  {getFieldDecorator('endTime', {
                    rules: [{
                      required: true, message: 'Pole wymagane',
                    }],
                    initialValue: this.props.appointment ? moment(this.props.appointment.endTime, this.format) : moment(this.props.slotInfo.end, this.format),
                  })(
                    <TimePicker format={this.format}/>,
                  )}
                </FormItem>
              {/*<FormItem*/}
              {/*{...formItemLayout}*/}
              {/*label="Numer domu/mieszkania"*/}
              {/*>*/}
              {/*{getFieldDecorator('houseAppartmentNumber')(*/}
              {/*<Input/>,*/}
              {/*)}*/}
              {/*</FormItem>*/}
              {/*<FormItem*/}
              {/*{...formItemLayout}*/}
              {/*label="Ulica"*/}
              {/*>*/}
              {/*{getFieldDecorator('street', {*/}
              {/*initialValue: this.props.appointment ? this.props.appointment.street : null*/}
              {/*})(*/}
              {/*<Input/>,*/}
              {/*)}*/}
              {/*</FormItem>*/}
              {/*<FormItem*/}
              {/*{...formItemLayout}*/}
              {/*label="Miejscowość"*/}
              {/*>*/}
              {/*{getFieldDecorator('city', {*/}
              {/*initialValue: this.props.appointment ? this.props.appointment.city : null*/}
              {/*})(*/}
              {/*<Input/>,*/}
              {/*)}*/}
              {/*</FormItem>*/}
              <FormItem
                {...formItemLayout}
                label={'Gabinet'}>
                {getFieldDecorator('consultingRoomId', {
                  initialValue: this.props.appointment ? this.props.appointment.consultingRoomId : null,
                })(
                  <Select>
                    {this.props.consultingRooms.map((cr) => <Option value={cr.id}
                                                                    key={cr.id}>{cr.roomNumber + ' ' + cr.description}</Option>)}
                  </Select>)}
              </FormItem>
                <FormItem
                    wrapperCol={{
                        xs: {span: 24, offset: 0},
                        sm: {span: 16, offset: 8},
                    }}
                >
                  <div className={'button-container'}>
                    <Button type="secondary" onClick={() => {
                      this.props.onCancel()
                    }}>Anuluj</Button>
                    <Button type="primary" htmlType="submit">Zapisz</Button>
                  </div>
                </FormItem>
            </Form>
        );
    }
}

const WrappedAppointmentForm = Form.create()(AppointmentForm);

export default WrappedAppointmentForm;
