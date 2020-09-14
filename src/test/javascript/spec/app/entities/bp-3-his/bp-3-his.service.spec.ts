import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { Bp3HisService } from 'app/entities/bp-3-his/bp-3-his.service';
import { IBp3His, Bp3His } from 'app/shared/model/bp-3-his.model';

describe('Service Tests', () => {
  describe('Bp3His Service', () => {
    let injector: TestBed;
    let service: Bp3HisService;
    let httpMock: HttpTestingController;
    let elemDefault: IBp3His;
    let expectedResult: IBp3His | IBp3His[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(Bp3HisService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Bp3His(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dco: currentDate.format(DATE_FORMAT),
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Bp3His', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dco: currentDate.format(DATE_FORMAT),
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dco: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.create(new Bp3His()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Bp3His', () => {
        const returnedFromService = Object.assign(
          {
            dco: currentDate.format(DATE_FORMAT),
            age: 'BBBBBB',
            dev: 'BBBBBB',
            ncp: 'BBBBBB',
            ope: 'BBBBBB',
            lib: 'BBBBBB',
            mon: 1,
            sen: 'BBBBBB',
            pie: 'BBBBBB',
            ncc: 'BBBBBB',
            uti: 'BBBBBB',
            utf: 'BBBBBB',
            nat: 'BBBBBB',
            nomFic: 'BBBBBB',
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dco: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Bp3His', () => {
        const returnedFromService = Object.assign(
          {
            dco: currentDate.format(DATE_FORMAT),
            age: 'BBBBBB',
            dev: 'BBBBBB',
            ncp: 'BBBBBB',
            ope: 'BBBBBB',
            lib: 'BBBBBB',
            mon: 1,
            sen: 'BBBBBB',
            pie: 'BBBBBB',
            ncc: 'BBBBBB',
            uti: 'BBBBBB',
            utf: 'BBBBBB',
            nat: 'BBBBBB',
            nomFic: 'BBBBBB',
            dateArrete: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dco: currentDate,
            dateArrete: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Bp3His', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
