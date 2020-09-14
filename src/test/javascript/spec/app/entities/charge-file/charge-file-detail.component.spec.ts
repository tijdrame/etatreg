import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { ChargeFileDetailComponent } from 'app/entities/charge-file/charge-file-detail.component';
import { ChargeFile } from 'app/shared/model/charge-file.model';

describe('Component Tests', () => {
  describe('ChargeFile Management Detail Component', () => {
    let comp: ChargeFileDetailComponent;
    let fixture: ComponentFixture<ChargeFileDetailComponent>;
    const route = ({ data: of({ chargeFile: new ChargeFile(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [ChargeFileDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ChargeFileDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChargeFileDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load chargeFile on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chargeFile).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
