import React, { Component } from 'react';
import { Table } from 'antd';


class PatientsList extends Component {

    constructor() {
        super()
        this.state = {
            'data' : []
        }
    }

    componentDidMount() {
        fetch('http://localhost:8080/patients/all')
            .then(res => res.json())
            .then(res => this.setState({'data': res}));
        console.log('hello kitty');

    }
    columns = [{
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
        render: text => <a href="http://google.com">{text}</a>
    },{
        title: 'Birthdate',
        dataIndex: 'birthdate',
        key: 'birthdate',
    },{
        title: 'Breed',
        dataIndex: 'breedId',
        key: 'breed',
    }];


    render() {
        const data = this.state.data;
        console.log(data);
        return (
            <div className={'margin-md'}>
                <h3>Lista pacjentów</h3>

                <Table dataSource={data}
                       columns={this.columns}
                       size="middle"
                />

            </div>
        )
    }

}

export default PatientsList
