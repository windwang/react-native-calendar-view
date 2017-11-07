import React, {Component} from 'react'
import {Dimensions, processColor, requireNativeComponent} from 'react-native'
import _ from 'lodash'
import {settingsLoadSuccess} from '../../App/Modules/User/Redux/ContactRedux'
import PropTypes from 'prop-types'
function convertProps(props) {

  if (props.fillDefaultColorDates) {
    const items = props.fillDefaultColorDates;
    props.fillDefaultColorDates = {};
    props.subtitleDefaultColorDates = {};
    props.subtitleForDates = {};
    _.each(items, (item, key) => {
      props.fillDefaultColorDates[key] = processColor(item.color);
      if (item.textColor) 
        props.subtitleDefaultColorDates[key] = item.textColor;
      if (item.text) 
        props.subtitleForDates[key] = item.text;
      }
    );
 
    props.lastUpdate=(new Date()).getTime()
   
    
  }

  let allKeys = [
    'fillSelectionColorDates',
    'borderDefaultColorDates',
    'borderSelectionColorDates',
    'titleDefaultColorDates',
    'titleSelectionColorDates',
    'subtitleDefaultColorDates',
    'subtitleSelectionColorDates'
  ];
  _.each(allKeys, (key) => {
    if (!props[key]) {
      return
    }
    _.each(props[key], (color, date) => {
      props[key][date] = processColor(color)
    })
  })
  if (props.today) {
    props.today = props
      .today
      .getTime()
  }
  if (props.dateBounds) {
    _.each(props.dateBounds, (value, index) => {
      if (value) {
        props.dateBounds[index] = value.getTime()
      }
    })
  }
  console.log("=====RNFSCalendar",props);

}

export default class FSCalendar extends Component {
  constructor(props) {
    super(props)
    this._props = _.assign({}, this.props)
    convertProps(this._props)
    const dt= new Date();
     this.props.onMonthChange && this
      .props
      .onMonthChange(new Date(dt.getFullYear(),dt.getMonth(),1))
  }

  static propTypes = {
    width: PropTypes.number,
    height: PropTypes.number,
    hideHeader: PropTypes.bool, // 是否隐藏头部
    hideWeekDay: PropTypes.bool, // 是否隐藏顶部的星期
    scrollEnabled: PropTypes.bool,
    today:PropTypes
      .instanceOf(Date),
    lastUpdate:PropTypes.number,
    dateBounds: PropTypes
      .arrayOf(Date),
    scopeMode: PropTypes
      .oneOf(['month', 'week']),
    borderRadius: PropTypes.number,

    subtitleTextSize: PropTypes.number,

    // 回调函数
    onSelectDate: PropTypes.func,
    onDeselectDate: PropTypes.func,
    onMonthChange: PropTypes.func,

    // 颜色样式
    headerTitleColor: PropTypes.string,
    weekdayTextColor: PropTypes.string,
    titleDefaultColor: PropTypes.string,
    titleSelectionColor: PropTypes.string,

    subtitleDefaultColor: PropTypes.string,
    subtitleSelectionColor: PropTypes.string,
    titleWeekendColor: PropTypes.string,
    subtitleWeekendColor: PropTypes.string,
    titleTodayColor: PropTypes.string,
    subtitleTodayColor: PropTypes.string,
    titlePlaceholderColor: PropTypes.string,
    subtitlePlaceholderColor: PropTypes.string,

    todayColor: PropTypes.string,
    todaySelectionColor: PropTypes.string,
    eventColor: PropTypes.string,
    eventDefaultColor: PropTypes.string,
    eventSelectionColor: PropTypes.string,
    borderDefaultColor: PropTypes.string,
    borderSelectionColor: PropTypes.string,
    selectionColor: PropTypes.string,

    // 针对每个单独的日期设置样式
    fillDefaultColorDates: PropTypes.object,
    fillSelectionColorDates: PropTypes.object,
    borderDefaultColorDates: PropTypes.object,
    borderSelectionColorDates: PropTypes.object,
    cellShapeDates: PropTypes.objectOf(PropTypes.number),

    titleDefaultColorDates: PropTypes.object,
    titleSelectionColorDates: PropTypes.object,
    subtitleDefaultColorDates: PropTypes.object,
    subtitleSelectionColorDates: PropTypes.object,

    subtitleForDates: PropTypes.object,

    // 头部样式
    headerMinimumDissolvedAlpha: PropTypes.number,
    headerDateFormat: PropTypes.string
  }

  static defaultProps = {
    width: Dimensions
      .get('window')
      .width,
    height: 280, // scopeMode 为 month 建议高度 280, 为 week 建议高度 70
    hideHeader: false,
    hideWeekDay: false,
    scrollEnabled: true,
    today: null,
    scopeMode: 'month',
    borderRadius: 0.15,
    lastUpdate:(new Date()).getTime()

  }

  componentWillReceiveProps(nextProps) {
    this._props = _.assign({}, nextProps)
    convertProps(this._props)
  }
  _onSelectDate(ev) {
    this.props.onSelectDate && this
      .props
      .onSelectDate(new Date(ev.nativeEvent.date))
  }
  _onDeselectDate(ev) {
    this.props.onDeselectDate && this
      .props
      .onDeselectDate(new Date(ev.nativeEvent.date))
  }
  _onCurrentPageChange(ev) {
    this.props.onMonthChange && this
      .props
      .onMonthChange(new Date(ev.nativeEvent.date))
  }

  render() {
    const {
      onSelectDate,
      onDeselectDate,
      onMonthChange,
      ...nativeProps
    } = this._props;
    return (<RNFSCalendar
      onSelectDate={(ev) => this._onSelectDate(ev)}
      onDeselectDate={(ev) => this._onDeselectDate(ev)}
      onCurrentPageChange={(ev) => this._onCurrentPageChange(ev)}
      { ...nativeProps }/>)
  }
}

const RNFSCalendar = requireNativeComponent('RNFSCalendar', {
  propTypes: {
    ...FSCalendar.propTypes,
    onCurrentPageChange: PropTypes.func,
    today: PropTypes.number
  }
})

export const CellShape = {
  Circle: 0,
  Rectangle: 1
}
