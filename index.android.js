import React, { Component, PropTypes } from 'react';
import { Dimensions, requireNativeComponent, View } from 'react-native';
import _ from 'lodash'
var RCTMaterialCalendarView = requireNativeComponent('RCTMaterialCalendarView', ReactMaterialCalendarView);

class ReactMaterialCalendarView extends Component {
  constructor() {
    super();
    this._onDateChange = this._onDateChange.bind(this);
    this._onMonthChange = this._onMonthChange.bind(this);
  }

  _onDateChange(event) {
    if (event.nativeEvent.selected&& this.props.onSelectDate) {
      this.props.onSelectDate && this.props.onSelectDate(event.nativeEvent.date);
    }else  if(!event.nativeEvent.selected&&this.props.onDeselectDate){
        this.props.onDeselectDate && this.props.onDeselectDate(event.nativeEvent.date);
    }
  }

  _onMonthChange(event) {
    if (this.props.onMonthChange) {
      this.props.onMonthChange && this.props.onMonthChange(event.nativeEvent.date);
    }
  }

  render() {
    var { style,hideHeader,fillDefaultColorDates, subtitleTextSize,...rest } = this.props,
      width = rest.width,
      height = rest.height ? rest.height : rest.topbarVisible ? width / 7 * 8 : width;

    style = {
      ...style,
      width,
      height
    };
     
     if(subtitleTextSize&& fillDefaultColorDates){
       _.each(fillDefaultColorDates,(value,key)=>{
          value.textSize=subtitleTextSize;
       })
     }
     console.log("fillDefaultColorDates====",fillDefaultColorDates)

    return (
      <RCTMaterialCalendarView
        {...rest}
        style={style}
        fillDefaultColorDates={fillDefaultColorDates}
        topbarVisible={!hideHeader}
        onDateChange={this._onDateChange}
        onMonthChange={this._onMonthChange} />
    );
  }
}

var FIRST_DAY_OF_WEEK = [
  'monday',
  'tuesday',
  'wednesday',
  'thursday',
  'friday',
  'saturday',
  'sunday'
];

var SHOWING_DATE = [
  'all',
  'none'
];

var SELECTION_MODES = [
  'none',
  'range',
  'single',
  'multiple'
];

const colorType = function (props, propName, componentName, ...rest) {
  var checker = function () {
    var color = props[propName];
    var regex = /^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$/;
    if (color && !regex.test(color)) {
      return new Error('Only accept color formats: #RRGGBB and #AARRGGBB');
    }
  };
  return PropTypes.string(props, propName, componentName, ...rest) || checker();
};

const ColorValidator = function (props, propName, componentName, ...rest) {
  var checker = function () {
    var color = props[propName];
    var regex = /^#([0-9A-Fa-f]{6}|[0-9A-Fa-f]{8})$/;
    if (color && !regex.test(color)) {
      return new Error('Color accept formats: #RRGGBB and #AARRGGBB');
    }
  };
  return PropTypes.string(props, propName, componentName, ...rest) || checker();
};

const DatesValidator = function (props, propName, componentName, ...rest) {
  console.log('DatesValidator');
  var checker = function () {
    var date = props[propName];
    var regex = /^(19|20)\d\d[/](0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])/;
    if (date && !regex.test(date)) {
      return new Error('Date should be: YYYY/MM/DD');
    }
  };
  return PropTypes.string(props, propName, componentName, ...rest) || checker();
};

ReactMaterialCalendarView.propTypes = {
  ...View.propTypes,
  width: PropTypes.number.isRequired,
  height: PropTypes.number,
  // Tile size
  tileWidth: PropTypes.number,
  tileHeight: PropTypes.number,
  tileSize: PropTypes.number,
  subtitleTextSize:PropTypes.number,
  // Toolbar options 
  hideHeader:PropTypes.bool,
  arrowColor: ColorValidator,
  // Calendar config
  firstDayOfWeek: PropTypes.oneOf(FIRST_DAY_OF_WEEK),
  minimumDate: DatesValidator,
  maximumDate: DatesValidator,
  datesSelection: PropTypes.oneOf(SELECTION_MODES),
  showOtherDates: PropTypes.oneOf(SHOWING_DATE),
  // Set date
  currentDate: DatesValidator,
  selectedDates: PropTypes.arrayOf(DatesValidator),
  eventsDates: PropTypes.arrayOf(DatesValidator),
  // Color customizations
  selectionColor: ColorValidator,
  weekendsColor: ColorValidator,
  eventsColor: ColorValidator,
 //custom day color
  fillDefaultColorDates:PropTypes.object
};

ReactMaterialCalendarView.defaultProps = {
  hideHeader: false,
  width: Dimensions.get('window').width,
  firstDayOfWeek:"sunday"
};

export default ReactMaterialCalendarView;