import React, { Component } from 'react';
import { Table } from 'antd';
import { getPatients } from "../clients/PatientClient";


class PatientsList extends Component {

    constructor() {
        super();
        this.state = {
            data : []
        }
    }
    componentWillMount(){
        getPatients().then(res => this.setState({data: res}))
    }
    columns = [{
            title: 'Imię',
            dataIndex: 'name',
            key: 'name',
            render: text => <a href="">{text}</a>
    },{
        title: 'Gatunek',
        dataIndex: 'breed.species',
        key: 'species',
    },{
        title: 'Rasa',
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
                       size="medium"
                />
            </div>
        )
    }
}

export default PatientsList
