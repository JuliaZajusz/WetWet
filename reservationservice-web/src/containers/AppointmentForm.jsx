import { Button, DatePicker, Form, Input, Select, TimePicker } from 'antd';
import React, { Component } from 'react';
import moment from 'moment';
import { saveAppointments } from '../clients/AppointmentClient';
import _ from 'lodash'
import ReactSelect from 'react-select';
import { Col, Row } from 'react-bootstrap'
import { getPatrons } from '../clients/PatronClient'
import { getPatronPets } from '../clients/PatientClient'
import { getConsultingRooms } from '../clients/ConsultingRoomClient';

const Option = Select.Option;
const FormItem = Form.Item;
const { MonthPicker, RangePicker } = DatePicker;

class AppointmentForm extends Component {
  constructor(props) {
    super();
    this.state = {
      consultingRooms: [],
      patients: [],
      patrons: [],
      selectedPatron: props.patron,
        dateTime: {
        date: props.appointment ? moment(props.appointment.date, 'YYYY-MM-DD') : moment(props.slotInfo.start, 'YYYY-MM-DD'),
            startTime: props.appointment ? moment(props.appointment.startTime, 'HH:mm') : moment(props.slotInfo.start, 'HH:mm'),
            endTime : props.appointment ? moment(props.appointment.endTime, 'HH:mm') : moment(props.slotInfo.end, 'HH:mm')
        }
    }
    props.patron && getPatronPets(props.patron.id).then((res) => this.setState({ patients: res }))

      let body = {
          date: this.state.dateTime.date.format('YYYY-MM-DD'),
        startTime: this.state.dateTime.startTime.format('HH:mm:ss'),
        endTime: this.state.dateTime.endTime.format('HH:mm:ss'),
      };
      getConsultingRooms(body).then((res) => this.setState({ consultingRooms: res }));
  }

    loadConsultingRooms = (date, dateString, field )=> {
        this.setState({dateTime:{...this.state.dateTime, [field]: dateString}})
        if(field === "date" ){
          this.setState({ dateTime: { ...this.state.dateTime, [field]: moment(dateString) } })
        }
        else if(field === "startTime" ){
          this.setState({ dateTime: { ...this.state.dateTime, [field]: moment(dateString, 'HH:mm') } })
        }
        else if(field === "endTime" ){
          this.setState({ dateTime: { ...this.state.dateTime, [field]: moment(dateString, 'HH:mm') } })
        }
        let body = {
          date: field === 'date' ? dateString : moment(this.state.dateTime.date).format('YYYY-MM-DD'),
          startTime: field === 'startTime' ? moment(dateString, 'HH:mm').format('HH:mm:ss') : this.state.dateTime.startTime.format('HH:mm:ss'),
          endTime: field === 'endTime' ? moment(dateString, 'HH:mm').format('HH:mm:ss') : this.state.dateTime.endTime.format('HH:mm:ss'),

        };
      getConsultingRooms(body).then((res) => {
        this.setState({ consultingRooms: res })
        if (!res.find(consultingRoom => consultingRoom.id === this.props.form.getFieldValue('consultingRoomId'))) {
          this.props.form.resetFields('consultingRoomId');
        }
      });


    }

  componentWillMount() {
    getPatrons().then((res) => this.setState({ patrons: res }));
  }

  componentDidMount() {
    this.props.setClick(this.getAlert);
    this.props.setCancelClick(this.handleCancel);
  }

  getAlert = () => {
    this.setState({
      dateTime: {
        date: this.props.appointment ? moment(this.props.appointment.date, 'YYYY-MM-DD') : moment(this.props.slotInfo.start, 'YYYY-MM-DD'),
        startTime: this.props.appointment ? moment(this.props.appointment.startTime, 'HH:mm') : moment(this.props.slotInfo.start, 'HH:mm'),
        endTime: this.props.appointment ? moment(this.props.appointment.endTime, 'HH:mm') : moment(this.props.slotInfo.end, 'HH:mm'),
      },
    })

    let body = {
      date: this.state.dateTime.date.format('YYYY-MM-DD'),
      startTime: this.state.dateTime.startTime.format('HH:mm:ss'),
      endTime: this.state.dateTime.endTime.format('HH:mm:ss'),
    };
    getConsultingRooms(body).then((res) => this.setState({ consultingRooms: res }));
  }

  format = 'HH:mm';
  dateFormat = 'YYYY-MM-DD';
  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, fieldsValue) => {
      if (err) {
        return;
      }

      let appointment = {
        'id': _.get(this.props, 'appointment.id'),
        'employeeId': fieldsValue.employeeId,
        'patronId': this.state.selectedPatron.id,
        'patientId': fieldsValue.patientId,
        'title': fieldsValue.title,
        'description': fieldsValue.description,
        'cost': 0,
        'date': fieldsValue.date,
        'startTime': fieldsValue.startTime.format('HH:mm:ss'),
        'endTime': fieldsValue.endTime.format('HH:mm:ss'),
        // addressPointId: 1,
        addressDTO: {
          'id': 1,
          'houseAppartmentNumber': '',
          'street': null,
          'city': null,
        },
        consultingRoomId: fieldsValue.consultingRoomId,
      }
      saveAppointments(appointment)
        .then(() => {
          this.setState({ selectedPatron: null })
            this.props.onOk()
          this.props.form.resetFields();
          },
        )
    });
  }

  handleCancel = () => {
    this.setState({ selectedPatron: null })
    this.props.form.resetFields();
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
    return (
      <div>
        <Row className={'row'}>
          <Col xs={'12'} sm={'4'} className={'card-label'}>Opiekun</Col>
          <Col xs={'12'} sm={'8'} className={'card-value'}>
            <ReactSelect
              name="form-field-name"
              value={{
                value: this.state.selectedPatron,
                label: this.state.selectedPatron ? this.state.selectedPatron.lastName + ' ' + this.state.selectedPatron.firstName : null,
              }
              }
              onChange={this.selectPatron}
              options={
                this.state.patrons.map((patron) => {
                  return {
                    value: patron,
                    label: patron.lastName + ' ' + patron.firstName,
                  }
                })
              }
            />
          </Col>
        </Row>


        <Form onSubmit={this.handleSubmit}>

          <FormItem
            {...formItemLayout}
            label={'Pacjent'}>
            {getFieldDecorator('patientId', {
              initialValue: this.props.appointment ? this.props.appointment.patientId : null,
            })(
              <Select>
                {this.state.patients.map((p) =>
                  <Option value={p.id} key={p.id}>
                    {p.name + ' ' + p.breed.name + ' ' + p.breed.species}
                  </Option>)
                }
              </Select>)}
          </FormItem>

          <FormItem
            {...formItemLayout}
            label={'Pracownik'}>
            {getFieldDecorator('employeeId', {
              initialValue: this.props.appointment ? this.props.appointment.employeeId : null,
            })(
              <Select>
                {this.props.employees.map((p) =>
                  <Option value={p.id} key={p.id}>
                    {p.firstName + ' ' + p.lastName}
                  </Option>)
                }
              </Select>)}
          </FormItem>

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
              initialValue: this.state.dateTime.date
            })(<DatePicker onChange={(date, dateString)=> this.loadConsultingRooms(date, dateString, "date")}
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
              initialValue: this.state.dateTime.startTime
            })(<TimePicker onChange={(date, dateString)=> this.loadConsultingRooms(date, dateString, "startTime")}
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
              initialValue: this.state.dateTime.endTime
            })(
              <TimePicker onChange={(date, dateString)=> this.loadConsultingRooms(date, dateString, "endTime")}
                  format={this.format}/>,
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
                {this.state.consultingRooms.map((cr) => <Option value={cr.id}
                                                                key={cr.id}>{cr.roomNumber + ' ' + cr.description}</Option>)}
              </Select>)}
          </FormItem>
          <FormItem
            wrapperCol={{
              xs: { span: 24, offset: 0 },
              sm: { span: 16, offset: 8 },
            }}
          >
            <div className={'button-container'}>
              <Button type="secondary" onClick={() => this.props.onCancel()
              }>Anuluj</Button>
              <Button type="primary" htmlType="submit">Zapisz</Button>
            </div>
          </FormItem>
        </Form>
      </div>
    );
  }

  selectPatron = (patron) => {
    this.setState({ selectedPatron: patron.value })
    getPatronPets(patron.value.id).then((res) => this.setState({ patients: res }))
  }
}

const WrappedAppointmentForm = Form.create()(AppointmentForm);

export default WrappedAppointmentForm;
