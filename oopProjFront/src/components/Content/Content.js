import React from 'react';
import { SubMenu } from '../SubMenu/SubMenu';
import { UserMenu } from '../UserMenu/UserMenu';
import 'bootstrap/dist/css/bootstrap.min.css';
import './content.css';
import axios from 'axios';

class Content extends React.Component{

	constructor(props) {
		super(props);
		this.state = {
			isLogged: true,
			username : '',
			password : '',
			projectList : [],
			studentList : [],
			taskList : [],
			projectMenu: false,
			studentMenu: false,
			taskMenu: false,
			addMenu: false,

		}
		this.handleChange = this.handleChange.bind(this);
		this.handleProjectList = this.handleProjectList.bind(this);
		this.handleStudentList = this.handleStudentList.bind(this);
		this.handleTaskList = this.handleTaskList.bind(this);
		this.resetMenus = this.resetMenus.bind(this);
		this.handleAddMenu = this.handleAddMenu.bind(this);
	}

	componentDidMount() {
	    console.log("Content component");
	}
	
	
	resetMenus = () =>{
		this.setState({projectMenu:false});
		this.setState({studentMenu:false});
		this.setState({taskMenu:false});
		this.setState({updateMenu:false});
		this.setState({addMenu:false});
	}
	
	handleChange(event) {
		const target = event.target;
		const value = target.value;
		const name = target.name;
		this.setState({
		  [name]: value    
		});
	}
	
	handleProjectList(event) {
		event.preventDefault();
		this.resetMenus();
		const backend_url = 'http://localhost:8081/api/projects';
		var config = {
			method: 'get',
			url: backend_url,
			headers: { 
				'Access-Control-Allow-Origin': '*',
				'Authorization': 'Basic YWRtaW46YWRtaW4=', 
				'Content-Type': 'application/json',
			}
		};

		axios(config)
		.then(response => {
			console.log(response.status);
			console.log(response.data.content);
			this.setState({projectList: response.data.content}, () => {
				this.setState({projectMenu: true})
			});
		})
	}
	
	handleStudentList(event) {
		event.preventDefault();
		this.resetMenus();
		const backend_url = 'http://localhost:8081/api/students';
		var config = {
			method: 'get',
			url: backend_url,
			headers: { 
				'Access-Control-Allow-Origin': '*',
				'Authorization': 'Basic YWRtaW46YWRtaW4=', 
				'Content-Type': 'application/json',
			}
		};

		axios(config)
		.then(response => {
			console.log(response.status);
			this.setState({studentList: response.data.content}, () => {
				this.setState({studentMenu: true})
			});
		})
	}
	
	handleTaskList(event) {
		event.preventDefault();
		this.resetMenus();
		const backend_url = 'http://localhost:8081/api/tasks';
		var config = {
			method: 'get',
			url: backend_url,
			headers: { 
				'Access-Control-Allow-Origin': '*',
				'Authorization': 'Basic YWRtaW46YWRtaW4=', 
				'Content-Type': 'application/json',
			}
		};

		axios(config)
		.then(response => {
			this.setState({taskList: response.data.content}, () => {
				this.setState({taskMenu: true})
			});
		})
	}
	
	handleAddMenu(event) {
		event.preventDefault();
		this.resetMenus();
		this.setState({addMenu: true});
	}


	render() {
		return (
			<div className="row-container">
				<div className="user-menu">
					<UserMenu isLogged={this.state.isLogged} handleProjectList={this.handleProjectList} handleStudentList={this.handleStudentList} handleTaskList={this.handleTaskList} handleAdd={this.handleAddMenu}/>
				</div>
				<div className = "content-menu">
					<SubMenu isLogged={this.state.isLogged} projectMenu={this.state.projectMenu} studentMenu={this.state.studentMenu} taskMenu={this.state.taskMenu} addMenu={this.state.addMenu} updateMenu={this.state.updateMenu} projectList={this.state.projectList} studentList={this.state.studentList} taskList={this.state.taskList}/>
				</div>
			</div>			
		);
	}
}

export {Content};