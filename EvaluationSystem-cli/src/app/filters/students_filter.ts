/**
 * Created by pedro on 29-06-2017.
 */
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'StudentsFilter',
})
export class StudentsFilter implements PipeTransform {
  transform(value: any, input: string) {
    if (input) {
      input = input.toLowerCase();
      return value.filter(function (el: any) {
        const pos = el.indexOf('@');
        const aux = el.substring(0, pos);
        return aux.toLowerCase().indexOf(input) > -1;
      });
    }
    return value;
  }
}

/*
 transform(value: any, input: string) {
 if (input) {
 input = input.toLowerCase();
 return value.filter(function (el: any) {
 const pos = el.email.indexOf('@');
 const aux = el.email.substring(0, pos);
 return aux.toLowerCase().indexOf(input) > -1;
 });
 }
 return value;
 }
 */
