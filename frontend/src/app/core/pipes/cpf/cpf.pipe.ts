import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cpf',
})
export class CpfPipe implements PipeTransform {
  transform(value: string): string {
    if (!value) {
      return '';
    }

    value = value.replace(/\D/g, '');

    while (value.length < 11) {
      value = '0' + value;
    }

    return `${value.substring(0, 3)}.${value.substring(3, 6)}.${value.substring(
      6,
      9
    )}-${value.substring(9)}`;
  }
}
