import React, { Component } from 'react';
import { Table } from 'antd';
import axios from 'axios'


class PatientsList extends Component {

    constructor() {
        super();
        this.state = {
            'data' : []
        }
    }
    componentDidMount() {
        axios.get('http://localhost:8080/api/patients/all')
            .then(res => this.setState({'data': res.data}))

    }
    columns = [{
        title: 'No.',
        dataIndex: 'id',
        key: 'id'
    },{
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            render: text => <a href="http://google.com">{text}</a>
    },{
        title: 'Species',
        dataIndex: 'breed.species.name',
        key: 'species',
    },{
        title: 'Breed',
        dataIndex: 'breed.name',
        key: 'breed',
    }];


    render() {
        const data = this.state.data;
        return (
            <div className={'margin-md'}>
                <h3>Lista pacjentów</h3>
                <Table dataSource={data}
                       columns={this.columns}
                       rowKey='id'
                       size="small"
                />
            </div>
        )
    }
}

export default PatientsList
