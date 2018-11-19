import {Button, DatePicker, Form, TimePicker} from 'antd';
import React, {Component} from "react";
import moment from "moment";
import {saveAppointments} from "../clients/AppointmentClient";

const FormItem = Form.Item;
const {MonthPicker, RangePicker} = DatePicker;

class AppointmentForm extends Component {
    format = 'HH:mm';
    dateFormat = 'YYYY-MM-DD';
    handleSubmit = (e) => {
        e.preventDefault();
        console.log(this.props.form.getFieldsValue())
        this.props.form.validateFields((err, fieldsValue) => {
            if (err) {
                return;
            }

            saveAppointments({name: "ddd"})
            console.log(fieldsValue)
            // Should format date value before submit.

            // const values = {
            //     ...fieldsValue,
            //     'date-picker': fieldsValue['date-picker'].format('YYYY-MM-DD'),
            //     'startTime-picker': fieldsValue['time-picker'].format('HH:mm'),
            //     'endTime-picker': fieldsValue['time-picker'].format('HH:mm'),
            // };
            // console.log('Received values of form: ', values);
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
        const config = {
            rules: [{
                type: 'object',
                required: true,
                message: 'Please select time!',
                defaultValue: moment('12:08', this.format),
                format: this.format
            }],
        };
        const rangeConfig = {
            rules: [{type: 'array', required: true, message: 'Please select time!'}],
        };
        console.log(moment(this.props.slotInfo.start, this.dateFormat))
        return (
            <Form onSubmit={this.handleSubmit}>
                <FormItem
                    {...formItemLayout}
                    label="Data wizyty"
                >

                    <DatePicker value={moment(this.props.slotInfo.start, this.dateFormat)} format={this.dateFormat}/>
                </FormItem>


                <FormItem
                    {...formItemLayout}
                    label="Początek wizyty"
                >
                    <TimePicker value={moment(this.props.slotInfo.start, this.format)} format={this.format}/>
                </FormItem>


                <FormItem
                    {...formItemLayout}
                    label="Koniec wizyty"
                >
                    <TimePicker value={moment(this.props.slotInfo.end, this.format)} format={this.format}/>
                </FormItem>
                <FormItem
                    wrapperCol={{
                        xs: {span: 24, offset: 0},
                        sm: {span: 16, offset: 8},
                    }}
                >
                    <Button type="primary" htmlType="submit">Submit</Button>
                </FormItem>
            </Form>
        );
    }
}

const WrappedAppointmentForm = Form.create()(AppointmentForm);

export default WrappedAppointmentForm;