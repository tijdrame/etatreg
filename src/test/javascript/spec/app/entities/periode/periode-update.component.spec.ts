import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { PeriodeUpdateComponent } from 'app/entities/periode/periode-update.component';
import { PeriodeService } from 'app/entities/periode/periode.service';
import { Periode } from 'app/shared/model/periode.model';

describe('Component Tests', () => {
  describe('Periode Management Update Component', () => {
    let comp: PeriodeUpdateComponent;
    let fixture: ComponentFixture<PeriodeUpdateComponent>;
    let service: PeriodeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [PeriodeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PeriodeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PeriodeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PeriodeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Periode(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Periode();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
