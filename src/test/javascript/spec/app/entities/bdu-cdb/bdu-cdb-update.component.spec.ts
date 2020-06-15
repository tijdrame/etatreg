import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EtatregTestModule } from '../../../test.module';
import { BduCdbUpdateComponent } from 'app/entities/bdu-cdb/bdu-cdb-update.component';
import { BduCdbService } from 'app/entities/bdu-cdb/bdu-cdb.service';
import { BduCdb } from 'app/shared/model/bdu-cdb.model';

describe('Component Tests', () => {
  describe('BduCdb Management Update Component', () => {
    let comp: BduCdbUpdateComponent;
    let fixture: ComponentFixture<BduCdbUpdateComponent>;
    let service: BduCdbService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EtatregTestModule],
        declarations: [BduCdbUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(BduCdbUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BduCdbUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BduCdbService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BduCdb(123);
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
        const entity = new BduCdb();
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
